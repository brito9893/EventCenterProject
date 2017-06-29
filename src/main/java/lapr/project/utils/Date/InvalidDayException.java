package lapr.project.utils.Date;

class InvalidDayException extends IllegalArgumentException {

    private static final long serialVersionUID = 5811908363590880473L;

    InvalidDayException(String message) {
        super(message);
    }
}
