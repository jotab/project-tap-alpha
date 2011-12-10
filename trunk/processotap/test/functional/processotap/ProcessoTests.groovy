package processotap;

import static org.junit.Assert.*;

class ProcessoTests extends functionaltestplugin.FunctionalTestCase{

	void testProcessoGet() {
		get '/processo'
		
		assertStatus 200
		assertContent '[]'
	}
	
	void testProcessoCriacao() {
		post('/processo') {
			headers['Content-Type'] = 'text/json'
			body { '{"numero":"1","autor":"Georgenes","reu":"Joao Bruno","descricao":"o processo tal e tal","decisao":"procedente"}' }
			
			
		}

		assertStatus 201 //retorna criado com sucesso
		assertContent '{"class":"processotap.Processo","id":1,"autor":"georgenes","decisao":"procedente","descricao":"o processo tal e tal","documentos":null,"numero":1,"reu":"joaobruno"}'
	}
	/*
	void testGetProcessoApos1Criacao () {
		get '/processo/1'
		
		assertStatus 200
		assertContent '[]'
	}
	
	void testGet() {
		get '/documento'
		
		assertStatus 200
		assertContent '[]'

	}
	
	
	void testCriacao() {
		post('/documento') {
			headers['Content-Type'] = 'text/json'
			body { '{"nome":"DesignPatterns"}' }
		}

		assertStatus 201 //retorna criado com sucesso
		assertContent '{"class":"processotap.Documento","id":1,"nome":"DesignPatterns"}'
	}

	void test2Criacao() {
		post('/documento') {
			headers['Content-Type'] = 'text/json'
			body { '{"nome":"Georgenes"}' }
		}

		assertStatus 201 //retorna criado com sucesso
		assertContent '{"class":"processotap.Documento","id":2,"nome":"Georgenes"}'
	}

	void testAfterPostGet() {
		get '/documento'
		
		assertStatus 200
		assertContent '[{"class":"processotap.Documento","id":1,"nome":"DesignPatterns"},{"class":"processotap.Documento","id":2,"nome":"Georgenes"}]'

	}
	
	void testRemove() {
		delete('/documento/1') {
		}

		assertStatus 204
//		assertContent 'removido'
	}

	void testAfterPost2Get() {
		get '/documento/1'
		
		assertStatus 404
	}

	void testAlteracao() {
		put('/documento/2') {
			headers['Content-Type'] = 'text/json'
			body { '{"nome":"Joao"}' }
		}

		assertStatus 200 //retorna criado com sucesso
		assertContent '{"class":"processotap.Documento","id":2,"nome":"Joao"}'
	}*/
}
