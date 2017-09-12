import fiscalizacionne.FiscalPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    fiscalPasswordEncoderListener(FiscalPasswordEncoderListener, ref('hibernateDatastore'))
}
