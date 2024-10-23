import http from "../http-common";

class RecruiterService {
  getAllRecruiters(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/recruiter/recruiters`, searchDTO);
  }

  get(recruiterId) {
    return this.getRequest(`/recruiter/${recruiterId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/recruiter?field=${matchData}`, null);
  }

  addRecruiter(data) {
    return http.post("/recruiter/addRecruiter", data);
  }

  update(data) {
  	return http.post("/recruiter/updateRecruiter", data);
  }
  
  uploadImage(data,recruiterId) {
  	return http.postForm("/recruiter/uploadImage/"+recruiterId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new RecruiterService();
