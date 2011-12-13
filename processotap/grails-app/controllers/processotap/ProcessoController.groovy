package processotap

import grails.converters.JSON

class ProcessoController {

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
		def processo = new Processo(params)
		if(processo.save()){
			response.status = 201 // Created
			def p = processo as JSON
			render p
		} else {
			response.status = 500 //Internal Server Error
			render "Nao foi possivel criar um novo processo devido aos seguintes erros:\n ${processo.errors}"
		}
	}
	private doGet() {
		if(params.chave) {
			def prosc = Processo.findById(params.chave) as JSON 
			render prosc
		} else {
			def prosc = Processo.list() as JSON
			render prosc
		}
	}
	private doPut() {
		def processo = Processo.findById(params.chave)
		processo.properties = params
		if(processo.save()) {
			response.status = 200 // OK
			render processo as JSON
		} else{
			response.status = 500 //Internal Server Error
			render "N�o foi poss�vel editar o processo devido aos seguintes erros:\n ${processo.errors}"
		}
	}
	private doDelete() {
		if(params.chave) {
			def processo = Processo.findById(params.chave)
			if(processo) {
				processo.delete()
				response.status = 204 // No content
				render "Removido com sucesso."
			} else {
				response.status = 404 //Not Found
				render "${params.chave} Nao encontrado."
			}
		} else {
			response.status = 400 //Bad Request
			render """A requisicao DELETE deve incluir o n�mero do processo
                                                  Exemplo: /
                                """
		}
	}
}
