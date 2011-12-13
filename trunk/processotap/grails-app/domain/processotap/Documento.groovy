package processotap

class Documento {
 
	String nome
	static belongsTo = [processo: Processo]
	
    static constraints = {
		nome(nullable: false, blank: false)
    }
}
