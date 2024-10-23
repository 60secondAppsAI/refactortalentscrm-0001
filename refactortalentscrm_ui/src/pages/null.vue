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
            <${classNameCamel}-table
            v-if="${classNameCamelPlural} && ${classNameCamelPlural}.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:${classNameCamelPlural}="${classNameCamelPlural}"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-${drcClass.name.snakeDash}s="getAll${drcClass.name.pascalPlural}"
             >

            </${classNameCamel}-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import ${className}Table from "@/components/${className}Table";
import ${className}Service from "../services/${className}Service";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ${className}Table,
  },
  data() {
    return {
      ${classNameCamelPlural}: [],
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
    async getAll${classNamePlural}(sortBy='${classNameCamel}Id',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ${className}Service.getAll${classNamePlural}(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.${classNameCamelPlural}.length) {
					this.${classNameCamelPlural} = response.data.${classNameCamelPlural};
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching ${classNameCamelPlural}:", error);
        }
        
      } catch (error) {
        console.error("Error fetching ${drcClass.name.camel} details:", error);
      }
    },
  },
  mounted() {
    this.getAll${classNamePlural}();
  },
  created() {
    this.$root.$on('searchQueryFor${classNamePlural}Changed', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAll${classNamePlural}();
    })
  }
};
</script>
<style></style>
