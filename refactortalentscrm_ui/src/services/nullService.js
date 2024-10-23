import http from "../http-common";

class ${className}Service {
  getAll${classNamePlural}(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/${classNameCamel}/${classNameCamelPlural}`, searchDTO);
  }

  get(${classNameCamel}Id) {
    return this.getRequest(`/${classNameCamel}/${${classNameCamel}Id}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/${classNameCamel}?field=${matchData}`, null);
  }

  add${className}(data) {
    return http.post("/${classNameCamel}/add${className}", data);
  }

  update(data) {
  	return http.post("/${classNameCamel}/update${className}", data);
  }
  
  uploadImage(data,${classNameCamel}Id) {
  	return http.postForm("/${classNameCamel}/uploadImage/"+${classNameCamel}Id, data);
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

export default new ${className}Service();
