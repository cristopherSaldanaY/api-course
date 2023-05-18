package tech.escalab.apicourse.common.exception;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorDetail {

    private String object;
    private String attribute;
    private String receivedValue;
    private String message;

    public ErrorDetail(){}

    public ErrorDetail(String object, String attribute, String receivedValue, String message){
        this.object = object;
        this.attribute = attribute;
        this.receivedValue = receivedValue;
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getReceivedValue() {
        return receivedValue;
    }

    public void setReceivedValue(String receivedValue) {
        this.receivedValue = receivedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDetail that = (ErrorDetail) o;
        return Objects.equals(object, that.object) && Objects.equals(attribute, that.attribute) && Objects.equals(receivedValue, that.receivedValue) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, attribute, receivedValue, message);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorDetail{");
        sb.append("object='").append(object).append('\'');
        sb.append(", attribute='").append(attribute).append('\'');
        sb.append(", receivedValue='").append(receivedValue).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static List<ErrorDetail> errorMap(List<FieldError> fieldErrors){

        List<ErrorDetail> errorResult = new ArrayList<>();  //lista personalizada

        for (FieldError fieldError : fieldErrors){ //recorremos el fieldError
            ErrorDetail errorDetail = new ErrorDetail();
            errorDetail.setAttribute(fieldError.getField());
            errorDetail.setObject(fieldError.getObjectName());
            errorDetail.setReceivedValue(fieldError.getRejectedValue().toString());
            errorDetail.setMessage(fieldError.getDefaultMessage());

            errorResult.add(errorDetail);
        }

        return errorResult; //retornamos los errores personalizados
    }
}
