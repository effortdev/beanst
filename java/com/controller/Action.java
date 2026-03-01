package com.controller;

import jakarta.servlet.http.*;

public interface Action {
    String execute(HttpServletRequest request,
                   HttpServletResponse response);
}