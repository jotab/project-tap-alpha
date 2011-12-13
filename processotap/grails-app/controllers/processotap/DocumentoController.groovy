package processotap

import grails.converters.JSON 

class DocumentoController {

	def index = {
		switch(request.method){
			case "GET":
				doGet()
				break
			case "POST":
				doPost()
				break

			case "PUT":
				doPut()
				break

			case "DELETE":
				doDelete()
				break
		}
	}
	private doPost() {
		def idProcesso = params.idProcesso
		def processo = Processo.findById(idProcesso)
		
		def documento = new Documento(params)
		documento.processo = processo
		processo.documentos.add(documento)

		if(documento.save()){
			response.status = 201 // Created
			def s = documento as JSON
			render s
		} else {
			response.status = 500 //Internal Server Error
			render "Nao foi possivel criar um novo documento devido aos seguintes erros:\n ${documento.errors}"
		}
	}
	private doGet() {
		if(params.chave) {
			def d = Documento.findById(params.chave)
			if (d) {
				render d as JSON
			}
		} else {
			render Documento.list() as JSON
		}
	}

	private doPut() {
		def documento = Documento.findById(params.chave)
		documento.properties = params
		if(documento.save()) {
			response.status = 200 // OK
			render documento as JSON
		} else{
			response.status = 500 //Internal Server Error
			render "Nao foi possivel editar o documento devido aos seguintes erros:\n ${documento.errors}"
		}
	}
	private doDelete() {
		if(params.chave) {
			def documento = Documento.findById(params.chave)
			if(documento) {
				documento.delete()
				response.status = 204 // No content
				//render 'removido'
			} else {
				response.status = 404 //Not Found
				//render "${params.chave} Nao encontrado."
			}
		} else {
			response.status = 400 //Bad Request
			//render """A requisicao DELETE deve incluir o nome do documento
            //                                      Exemplo: /documento/
            //                    """
		}
	}
}
