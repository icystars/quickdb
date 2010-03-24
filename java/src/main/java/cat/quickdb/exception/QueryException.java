package cat.quickdb.exception;

public class QueryException extends RuntimeException{

    public QueryException() {
        super("Query malformed exception");
    }

    public QueryException(Throwable t) {
        super("Query malformed exception", t);
    }

}
