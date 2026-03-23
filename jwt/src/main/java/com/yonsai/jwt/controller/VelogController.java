package com.yonsai.jwt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class VelogController {
    
    @GetMapping("/velog")
    public String getMethodName() {
        
        // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNTU3ZGUyODYtNGJiNS00ZjU4LTgyYTMtZGIzYzVjMGE0ZTU2IiwiaWF0IjoxNzc0MjM0ODE0LCJleHAiOjE3NzQzMjEyMTQsImlzcyI6InZlbG9nLmlvIiwic3ViIjoiYWNjZXNzX3Rva2VuIn0.ydbWeDnog2moIWxqN3-Pij-WHRxFNja0mThXZ84l2Tc";
        


        return "velog 작성완료";
    }
    

}
