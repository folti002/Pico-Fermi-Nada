function setCookie(cookieName, cookieValue, daysValid){
  var date = new Date();
  date.setTime(date.getTime() + (daysValid * 24 * 60 * 60 * 1000));
  var expires = "expires=" + date.toUTCString();
  document.cookie = cookieName + "=" + cookieValue + ";" + expires + ";path=/";
}

function getCookie(username){
  var name = username + "=";
  var cookieArray = document.cookie.split(';');
  for(elem in cookieArray){
    var cookie = cookieArray[elem];
    while(cookie.charAt(0) == ' '){
      cookie = cookie.substring(1);
    }
    if(cookie.indexOf(name) == 0){
      return cookie.substring(name.length, cookie.length);
    }
  }
  return "";
}

function checkCookies() {
  var username = getCookie("username");
  var org = getCookie("organization");
  if(username != "" && org != ""){
    alert("Welcome back " + username + " from " + org);
  } else {
    username = prompt("Please enter your name", "");
    if(username != "" && username != null){
      setCookie("username", username, 365);
    }
    org = prompt("Please enter your organization", "");
    if(org != "" && org != null){
      setCookie("organization", org, 365);
    }
  }
}

function deleteCookie() {
  setCookie("username", "", -1);
  setCookie("organization", "", -1);
  console.log("User logged out! Redirecting...");
  window.location.href = "http://google.com";
}