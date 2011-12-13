package processotap;

import static org.junit.Assert.*;
import grails.converters.JSON

class ProcessoTests extends functionaltestplugin.FunctionalTestCase{

	void testCompleto() {
		//TESTE 1 - no get processo vazio - GET /A - retorna todos os A
		get '/processo'
		
		assertStatus 200
		assertContent '[]'

		
		//TESTE 2 - no get documento vazio - GET /B - retorna todos os B		
		get '/documento'
		
		assertStatus 200
		assertContent '[]'

		//TEST 3 - criando processo e documento referente ao processo -  POST /A - cria um A
		post('/processo') {
			headers['Content-Type'] = 'text/json'
			body { '{"numero":"1","autor":"Georgenes","reu":"Joao Bruno","descricao":"o processo tal e tal","decisao":"procedente"}' }
		}

		assertStatus 201 //retorna criado com sucesso - 

		//TESTE 4 - POST /B - cria um B
		post('/documento') {
			headers['Content-Type'] = 'text/json'
			body { '{"nome":"DesignPatterns","idProcesso":"1"}' }

			assertStatus 201
		}


		//TESTE 5 - fazendo um get no processo criado - GET /A/id - retorna um A pelo id
		get '/processo/1'
		
		assertStatus 200
		assertContent '{"class":"processotap.Processo","id":1,"autor":"georgenes","decisao":"procedente","descricao":"o processo tal e tal","documentos":[{"class":"documento","id":1}],"numero":1,"reu":"joaobruno"}'

		
		//TESTE 6 - fazendo um get em todos os processos - GET /A - retorna todos os A
		get '/processo'
		
		assertStatus 200
		assertContent '[{"class":"processotap.processo","id":1,"autor":"georgenes","decisao":"procedente","descricao":"oprocessotaletal","documentos":[{"class":"documento","id":1}],"numero":1,"reu":"joaobruno"}]'

		//TESTE 7 - fazendo um get no documento criado - GET /B/id - retorna um B pelo id
		get('/documento/1')
		
		assertStatus 200
		assertContent '{"class":"processotap.Documento","id":1,"nome":"DesignPatterns","processo":{"class":"processo","id":1}}'


		//TESTE 8 - fazendo um get em todos os documentos criados - GET /A - retorna todos os A
		get '/documento'
		
		assertStatus 200
		assertContent '[{"class":"processotap.Documento","id":1,"nome":"DesignPatterns","processo":{"class":"processo","id":1}}]'


		//TESTE 9 - PUT EM B
		put('/documento/1') {
			headers['Content-Type'] = 'text/json'
			body { '{"nome":"Joao"}' }
		}

		assertStatus 200 //retorna criado com sucesso
		assertContent '{"class":"processotap.Documento","id":1,"nome":"Joao","processo":{"class":"processo","id":1}}'

		//TESTE 10 - PUT EM A
		put('/processo/1') {
			headers['Content-Type'] = 'text/json'
			body { '{"numero":"1","autor":"nome alterado","reu":"nome do reu alterado","descricao":"processo alterado","decisao":"procedente"}' }
		}

		assertStatus 200 //retorna alterado com sucesso

		//TESTE 11 - DELETE /A/id - apaga um A pelo id
 		delete ('/processo/1') {
		}
		assertStatus 204	

	}
 	
}
