package fiscalizacionne
class CorsInterceptor {

    CorsInterceptor() {
        matchAll()
    }

    boolean before() {
        response.setHeader("Access-Control-Allow-Origin", "*")
        if (request.method == "OPTIONS") {
            response.setHeader("Access-Control-Allow-Origin", "*")
            response.setHeader("Access-Control-Allow-Credentials", "true")
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")

            response.status = 200
        }

        return true
    }

    boolean after() { true }
}