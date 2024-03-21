<%-- 
    Document   : Login
    Created on : Oct 20, 2023, 3:13:21 AM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css"
            rel="stylesheet"
            />
        <style>
            .container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                min-height: 56vh;
                padding: 20px;
                box-shadow: 0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23);
                background-color: #fff;
                border-radius: 8px;
                margin: 100px auto;
                width: 80%;
                max-width: 500px;
            }

        </style>
    </head>
    <body>
        <div class ="container">
            <!-- Pills navs -->
            <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="tab-login" data-mdb-toggle="pill" href="#pills-login" role="tab"
                       aria-controls="pills-login" aria-selected="true">Login</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="tab-register" data-mdb-toggle="pill" href="#pills-register" role="tab"
                       aria-controls="pills-register" aria-selected="false">Register</a>
                </li>
            </ul>
            <!-- Pills navs -->

            <!-- Pills content -->
            <div class="tab-content" style="width:80%;">
                <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                    <form  action="LoginServlet" method="post">
                        <div class="text-center mb-3">
                            <h5 style="color: red; font-size: 15px;">${requestScope.errorMessage1}</h5>
                            <h5 style="color: red; font-size: 15px;">${requestScope.errorMessage2}</h5>
                            <p>Sign in with:</p>
                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-facebook-f"></i>
                            </button>

                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-google"></i>
                            </button>

                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-twitter"></i>
                            </button>

                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-github"></i>
                            </button>
                        </div>

                        <p class="text-center">or:</p>

                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <input type="text" name="email" id="loginName" class="form-control" />
                            <label class="form-label" for="loginName">Email or username</label>
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <input type="password" name="password" id="loginPassword" class="form-control" />
                            <label class="form-label" for="loginPassword">Password</label>
                        </div>
                        <h5 style="color: red; font-size: 15px;">${requestScope.errorMessage}</h5>

                        <!-- 2 column grid layout -->
                        <div class="row mb-4">
                            <div class="col-md-6 d-flex justify-content-center">
                                <!-- Checkbox -->
                                <div class="form-check mb-3 mb-md-0">
                                    <input class="form-check-input" type="checkbox" value="" id="loginCheck" checked />
                                    <label class="form-check-label" for="loginCheck"> Remember me </label>
                                </div>
                            </div>

                            <div class="col-md-6 d-flex justify-content-center">
                                <!-- Simple link -->
                                <a href="ForgetPass">Forgot password?</a>
                            </div>
                        </div>

                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>

                        <!-- Register buttons -->
                        <div class="text-center">
                            <p>Not a member? Register on the header</p>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="pills-register" role="tabpanel" aria-labelledby="tab-register">
                    <form action="RegisterServlet" method="post">
                        <div class="text-center mb-3">
                            <p>Sign up with:</p>
                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-facebook-f"></i>
                            </button>

                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-google"></i>
                            </button>

                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-twitter"></i>
                            </button>

                            <button type="button" class="btn btn-link btn-floating mx-1">
                                <i class="fab fa-github"></i>
                            </button>
                        </div>

                        <p class="text-center">or:</p>

                        <!-- Name input -->
                        <div class="form-outline mb-4">
                            <input type="text" name="name1" id="registerName" class="form-control"/>
                            <label class="form-label" for="registerName">Name</label>
                        </div>

                        <!-- Username input -->
                        <div class="form-outline mb-4">
                            <input type="text" name="username1" id="registerUsername" class="form-control"/>
                            <label class="form-label" for="registerUsername">Username</label>
                        </div>

                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <input type="email" name="email1" id="registerEmail" class="form-control"/>
                            <label class="form-label" for="registerEmail">Email</label>
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <input type="password" name="password1" id="registerPassword" class="form-control"/>
                            <label class="form-label" for="registerPassword">Password</label>
                        </div>

                        <!-- Repeat Password input -->
                        <div class="form-outline mb-4">
                            <input type="password" id="registerRepeatPassword" class="form-control" onkeyup="checkPasswords()" />
                            <label class="form-label" for="registerRepeatPassword">Repeat password</label>
                        </div>

                        <!-- Checkbox -->
                        <div class="form-check d-flex justify-content-center mb-4">
                            <input class="form-check-input me-2" type="checkbox" value="" id="registerCheck"
                                   aria-describedby="registerCheckHelpText" onclick="checkPasswords()" />
                            <label class="form-check-label" for="registerCheck">
                                I have read and agree to the terms
                            </label>
                        </div>

                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block mb-3">Sign up</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- Pills content -->
        <!-- Pills content -->
        <script type="text/javascript">
            function checkPasswords() {
                const passwordInput = document.getElementById('registerPassword');
                const repeatPasswordInput = document.getElementById('registerRepeatPassword');
                const nameInput = document.getElementById('registerName');
                const usernameInput = document.getElementById('registerUsername');
                const emailInput = document.getElementById('registerEmail');
                const submitButton = document.querySelector('#pills-register button[type="submit"]');

                let errorMessage = ''; // For collecting error messages

                if (passwordInput.value !== repeatPasswordInput.value) {
                    // If passwords do not match
                    repeatPasswordInput.style.borderColor = 'red';
                    errorMessage += 'Passwords do not match. ';
                    submitButton.disabled = true;
                } else {
                    // If passwords match
                    repeatPasswordInput.style.borderColor = 'green';
                }

                // Check if inputs are empty
                if (!nameInput.value.trim()) {
                    errorMessage += 'Name is required. ';
                }
                if (!usernameInput.value.trim()) {
                    errorMessage += 'Username is required. ';
                }
                if (!emailInput.value.trim()) {
                    errorMessage += 'Email is required. ';
                }
                if (!passwordInput.value.trim()) {
                    errorMessage += 'Password is required. ';
                }

                if (errorMessage) {
                    alert(errorMessage);
                    submitButton.disabled = true;
                    document.getElementById('registerCheck').checked = false;
                } else {
                    submitButton.disabled = false;
                }
            }
        </script>


        <!-- MDB -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.js"
        ></script>

    </body>
</html>
