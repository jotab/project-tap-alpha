package processotap

import grails.converters.JSON

class ProcessoController {

        def index = {
                switch(request.method){
                        case "POST":
                                doPost()
                                break
                        case "GET":
                                doGet()
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
                def proceso = new Processo(params)
                if(processo.save()){
                        response.status = 201 // Created
						def p = processo as JSON
                        render p
                } else {
                        response.status = 500 //Internal Server Error
                        render "N�o foi poss�vel criar um novo processo devido aos seguintes erros:\n ${processo.errors}"
                }
        }
        private doGet() {
                if(params.chave) {
                        render Processo.findById(params.chave) as JSON
                } else {
                        render Processo.list() as JSON
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
                        def documento = Processo.findById(params.chave)
                        if(processo) {
                                processo.delete()
								response.status = 204 // No content
                                render "Removido com sucesso."
                        } else {
                                response.status = 404 //Not Found
                                render "${params.chave} N�o encontrado."
                        }
                } else {
                        response.status = 400 //Bad Request
                        render """A requisi��o DELETE deve incluir o n�mero do processo
                                                  Exemplo: /
                                """
                }
        }
}
