package by.tms.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String type;
    private String message;

    public ApiResponse(String message){
        this.message = message;
    }

}