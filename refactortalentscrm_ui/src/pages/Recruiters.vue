<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <recruiter-table
            v-if="recruiters && recruiters.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:recruiters="recruiters"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-recruiters="getAllRecruiters"
             >

            </recruiter-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import RecruiterTable from "@/components/RecruiterTable";
import RecruiterService from "../services/RecruiterService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RecruiterTable,
  },
  data() {
    return {
      recruiters: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllRecruiters(sortBy='recruiterId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RecruiterService.getAllRecruiters(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.recruiters.length) {
					this.recruiters = response.data.recruiters;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching recruiters:", error);
        }
        
      } catch (error) {
        console.error("Error fetching recruiter details:", error);
      }
    },
  },
  mounted() {
    this.getAllRecruiters();
  },
  created() {
    this.$root.$on('searchQueryForRecruitersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRecruiters();
    })
  }
};
</script>
<style></style>
