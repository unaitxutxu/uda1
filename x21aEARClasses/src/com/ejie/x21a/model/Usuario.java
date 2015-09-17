package com.ejie.x21a.model;


import com.ejie.x38.control.JsonDateDeserializer;
import com.ejie.x38.control.JsonDateSerializer;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
*  * Usuario generated by UDA 1.0, 26-may-2011 13:45:00.
* @author UDA
 */

public class Usuario  implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
         private String id;
         private String nombre;
         private String apellido1;
         private String apellido2;
         private String ejie;
         private Date fechaAlta;
         private Date fechaBaja;

/** Method 'Usuario'.
*
*/
    public Usuario() {
    }

    /** Method 'Usuario'.
    * @param  id String
    * @param  nombre String
    * @param  apellido1 String
    * @param  apellido2 String
    * @param  ejie String
    * @param  fechaAlta Date
    * @param  fechaBaja Date
    */
   public Usuario(String id, String nombre, String apellido1, String apellido2, String ejie, Date fechaAlta, Date fechaBaja) {	

           this.id = id;
           this.nombre = nombre;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
           this.ejie = ejie;
           this.fechaAlta = fechaAlta;
           this.fechaBaja = fechaBaja;
    }

    /**
     * Method 'getId'.
     *
     * @return String
     */
	 
    
    public String getId() {
       return this.id;
     }

    /**
     * Method 'setId'.
     *
     * @param id String
     */
    
    public void setId(String id) {
      this.id = id;
    }
    	
    /**
     * Method 'getNombre'.
     *
     * @return String
     */
	 
    
    public String getNombre() {
       return this.nombre;
     }

    /**
     * Method 'setNombre'.
     *
     * @param nombre String
     */
    
    public void setNombre(String nombre) {
      this.nombre = nombre;
    }
    	
    /**
     * Method 'getApellido1'.
     *
     * @return String
     */
	 
    
    public String getApellido1() {
       return this.apellido1;
     }

    /**
     * Method 'setApellido1'.
     *
     * @param apellido1 String
     */
    
    public void setApellido1(String apellido1) {
      this.apellido1 = apellido1;
    }
    	
    /**
     * Method 'getApellido2'.
     *
     * @return String
     */
	 
    
    public String getApellido2() {
       return this.apellido2;
     }

    /**
     * Method 'setApellido2'.
     *
     * @param apellido2 String
     */
    
    public void setApellido2(String apellido2) {
      this.apellido2 = apellido2;
    }
    	
    /**
     * Method 'getEjie'.
     *
     * @return String
     */
	 
    
    public String getEjie() {
       return this.ejie;
     }

    /**
     * Method 'setEjie'.
     *
     * @param ejie String
     */
    
    public void setEjie(String ejie) {
      this.ejie = ejie;
    }
    	
    /**
     * Method 'getFechaAlta'.
     *
     * @return Date
     */
	 
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getFechaAlta() {
       return this.fechaAlta;
     }

    /**
     * Method 'setFechaAlta'.
     *
     * @param fechaAlta Date
     */
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setFechaAlta(Date fechaAlta) {
      this.fechaAlta = fechaAlta;
    }
    	
    /**
     * Method 'getFechaBaja'.
     *
     * @return Date
     */
	 
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getFechaBaja() {
       return this.fechaBaja;
     }

    /**
     * Method 'setFechaBaja'.
     *
     * @param fechaBaja Date
     */
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setFechaBaja(Date fechaBaja) {
      this.fechaBaja = fechaBaja;
    }
    	

/**
* Intended only for logging and debugging.
* 
* Here, the contents of every main field are placed into the result.
* @return String
*/
@Override
 public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(this.getClass().getName()).append(" Object { " ); 
				result.append(" [ id: ").append(this.id).append(" ]");
				result.append(", [ nombre: ").append(this.nombre).append(" ]");
				result.append(", [ apellido1: ").append(this.apellido1).append(" ]");
				result.append(", [ apellido2: ").append(this.apellido2).append(" ]");
				result.append(", [ ejie: ").append(this.ejie).append(" ]");
				result.append(", [ fechaAlta: ").append(this.fechaAlta).append(" ]");
				result.append(", [ fechaBaja: ").append(this.fechaBaja).append(" ]");
	result.append("}");
    return result.toString();
     }


}
