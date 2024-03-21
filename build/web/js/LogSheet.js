// Hiển thị form đăng nhập
document.getElementById("showLogin").addEventListener("click", function(){
    document.getElementById("loginModal").style.display = "block";
  });
  
  // Hiển thị form đăng ký
  document.getElementById("showSignup").addEventListener("click", function(){
    document.getElementById("signupModal").style.display = "block";
  });
  
  // Đóng form đăng nhập khi nhấp vào dấu x
  document.getElementById("closeLogin").addEventListener("click", function(){
    document.getElementById("loginModal").style.display = "none";
  });
  
  // Đóng form đăng ký khi nhấp vào dấu x
  document.getElementById("closeSignup").addEventListener("click", function(){
    document.getElementById("signupModal").style.display = "none";
  });
  
  // Đóng form đăng nhập khi nhấp ngoài form
  document.getElementById("loginModal").addEventListener("click", function(event){
    if(event.target == document.getElementById("loginModal")) {
      document.getElementById("loginModal").style.display = "none";
    }
  });
  
  // Đóng form đăng ký khi nhấp ngoài form
  document.getElementById("signupModal").addEventListener("click", function(event){
    if(event.target == document.getElementById("signupModal")) {
      document.getElementById("signupModal").style.display = "none";
    }
  });
  