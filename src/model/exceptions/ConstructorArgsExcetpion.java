package model.exceptions;

public class ConstructorArgsExcetpion extends IllegalArgumentException {
    private static final long serialVersionUID = 8704140456486202229L;

    public ConstructorArgsExcetpion(String message) {
        super(message);
    }

}
