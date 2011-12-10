package processotap

class Documento {
 
	String nome
	static belongsTo = [processso: Processo]
	
    static constraints = {
		nome(nullable: false, blank: false)
    }
}
