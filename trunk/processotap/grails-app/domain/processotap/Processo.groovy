package processotap

class Processo {
	 
		long numero
		String autor
		String reu
		String descricao
		String decisao
		
		static hasMany = [documentos: Documento]
		
		static constraints = {
			numero (nullable: false, blank: false)
			autor (nullable: false, blank: false)
			reu (nullable: false, blank: false)
			descricao (nullable: false, blank: false, size:3..1000)
			decisao (nullable: true, blank: true, size:3..500)
		}
		
		String toString() {
			return "${numero}"
		}
	}
	