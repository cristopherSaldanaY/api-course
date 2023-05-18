package tech.escalab.apicourse.common.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ErrorHttp {

    private int status;
    private LocalDateTime date;
    private List<ErrorDetail> errors;
    private String route;


    public ErrorHttp(){}

    public ErrorHttp(int status, LocalDateTime date, List<ErrorDetail> errors, String route){
        this.status = status;
        this.date = date;
        this.errors = errors;
        this.route = route;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorHttp errorHttp = (ErrorHttp) o;
        return status == errorHttp.status && Objects.equals(date, errorHttp.date) && Objects.equals(errors, errorHttp.errors) && Objects.equals(route, errorHttp.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, date, errors, route);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorHttp{");
        sb.append("status=").append(status);
        sb.append(", date=").append(date);
        sb.append(", errors=").append(errors);
        sb.append(", route='").append(route).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
