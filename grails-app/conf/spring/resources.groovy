import com.google.gson.GsonBuilder
import fiscalizacionne.FiscalPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    fiscalPasswordEncoderListener(FiscalPasswordEncoderListener, ref('hibernateDatastore'))
    gsonBuilder(GsonBuilder){}
}
