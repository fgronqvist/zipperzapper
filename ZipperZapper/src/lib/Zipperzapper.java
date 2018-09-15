package lib;

/**
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class Zipperzapper {    
    private int compression_option;
    private String inpufile;
    private String outputfile;
    
    /**
     *
     * @throws java.lang.Exception
     */
    public void run() throws Exception{
        if(!this.checkOptions()){
            throw new Exception("The options are not ok.");
        }
        
        Compress compress = new Compress();
        if(this.getCompressionOption() == 2){
            compress.javaUtilCompress(this.getInpufile(), this.getOutputfile());
        }
    }
    
    /**
     * Sanity check for the object.
     * @return 
     */
    private boolean checkOptions(){
        if(this.getCompressionOption() == 0){
            return false;
        }
        
        if("".equals(this.getInpufile())){
            return false;
        }
        
        if("".equals(this.getOutputfile())){
            return false;
        }        
        
        return true;
    }

    /**
     * @return the compression_option
     */
    public int getCompressionOption() {
        return compression_option;
    }

    /**
     * @param compression_option the compression_option to set
     */
    public void setCompressionOption(int compression_option) {
        this.compression_option = compression_option;
    }

    /**
     * @return the inpufile
     */
    public String getInpufile() {
        return inpufile;
    }

    /**
     * @param inpufile the inpufile to set
     */
    public void setInpufile(String inpufile) {
        this.inpufile = inpufile;
    }

    /**
     * @return the outputfile
     */
    public String getOutputfile() {
        return outputfile;
    }

    /**
     * @param outputfile the outputfile to set
     */
    public void setOutputfile(String outputfile) {
        this.outputfile = outputfile;
    }
    
    
}
