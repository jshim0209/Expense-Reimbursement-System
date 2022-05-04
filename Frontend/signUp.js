let signUpBtn = document.querySelector('#signUp-btn');

signUpBtn.addEventListener('click', async () =>{

    let usernameInput = document.querySelector('#username-input');
    let passwordInput = document.querySelector('#password-input');
    let firstNameInput = document.querySelector('#firstName-input');
    let lastNameInput = document.querySelector('#lastName-input');
    let emailInput = document.querySelector('#email-input');
    let userRoleInput = document.querySelector('#userRole-input');

    const URL = 'http://localhost:8081/signUp';
    const jsonString = JSON.stringify({
        "username" : usernameInput.value,
        "password" : passwordInput.value,
        "firstName" : firstNameInput.value,
        "lastName" : lastNameInput.value,
        "email" : emailInput.value,
        "user_role" : userRoleInput.value

    });

    let res = await fetch(URL, {
        method: 'POST',
        body: jsonString,
    });


    if (res.status === 200) {
        let user = await res.json();

        let token = res.headers.get('Token');
        localStorage.setItem('jwt', token);
        localStorage.setItem('user_id', user.user_id); 
        localStorage.setItem('user_role', user.user_role);

        if (user.user_role === 'finance_manager') {
            window.location = './financeManagerPage.html';
        } else if (user.user_role === 'employee') {
            window.location = './employeePage.html';
        }
    } else {
        let errorMsg = await res.text();
        console.log(errorMsg);
    
        let errorElement = document.querySelector('#error-msg');
        errorElement.innerText = errorMsg;
        errorElement.style.color = 'red';
    }
});



// 'Enter' key set up
var input = document.getElementById("userRole-input");

// Execute a function when the user releases a key on the keyboard
input.addEventListener("keyup", function(event) {
  // Number 13 is the "Enter" key on the keyboard
  if (event.keyCode === 13) {
    // Cancel the default action, if needed
    event.preventDefault();
    // Trigger the button element with a click
    document.getElementById("signUp-btn").click();
  }
});