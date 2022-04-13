
let logoutBtn = document.querySelector('#logout-btn');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('user_role');
    localStorage.removeItem('user_id');
    localStorage.removeItem('jwt');

    window.location.href = './index.html';
});

let ticketSubmit = document.querySelector('#a-submit');

ticketSubmit.addEventListener('click', async () => {
    let reimbursementAmountInput = document.querySelector('#a-amount-input');
    let reimbursementDescriptionInput = document.querySelector('#a-description-input');
    let reimbursementTypeIdInput = document.querySelector('#a-typeId-input');
    let receiptInput = document.querySelector('#a-file-input');

    let formData = new FormData();
    formData.append('amount', reimbursementAmountInput.value);
    formData.append('description', reimbursementDescriptionInput.value);
    formData.append('typeId', reimbursementTypeIdInput.value);
    formData.append('receipt', receiptInput.files[0]);

    try {
        let res = await fetch(`http://localhost:8081/users/${localStorage.getItem('user_id')}/reimbursements`, {
            method: 'POST',
            body: formData,
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            }

        });

        populateReimbursementsTable();
    } catch (e) {
        console.log(e);
   }
})


window.addEventListener('load', (event) => {
    populateReimbursementsTable();
});

async function populateReimbursementsTable() {
    const URL = `http://localhost:8081/users/${localStorage.getItem('user_id')}/reimbursements`;


    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}` 
        }
    })

    if (res.status === 200) {

        
        let reimbursements = await res.json();

        let tbody = document.querySelector('#employee-reimbursements-tbl > tbody');
        tbody.innerHTML = '';

        for (let reimbursement of reimbursements) {
            let tr = document.createElement('tr');

            let td1 = document.createElement('td');
            td1.innerText = reimbursement.id;


            let td2 = document.createElement('td');
            td2.innerText = reimbursement.amount;


            let td3 = document.createElement('td');
            td3.innerText = reimbursement.timeSubmitted;


            let td4 = document.createElement('td');
            td4.innerText = reimbursement.description;


            let td5 = document.createElement('td');
            td5.innerText = reimbursement.firstName;

            let td6 = document.createElement('td');
            td6.innerText = reimbursement.lastName;


            let td7 = document.createElement('td');
            if(reimbursement.status == "aproved"){
                td7.innerText = "Approved"
            }
            if(reimbursement.status == "pending"){
                td7.innerText = "Pending"
            }
            if(reimbursement.status == "denied"){
                td7.innerText = "Rejected"
            }
            
            let td8 = document.createElement('td');
            if (reimbursement.type == "lodging") {
                td8.innerText = "Lodging";
                
            }
            if (reimbursement.type == "travel") {
                td8.innerText = "Travel";
                
            }
            if (reimbursement.type == "food") {
                td8.innerText = "Food";
                
            }
            if (reimbursement.type == "other") {
                td8.innerText = "Other";
                
            }

            let receipt = document.createElement('td');
            let imgElement = document.createElement('img');
            imgElement.setAttribute('src', `http://localhost:8081/reimbursements/${reimbursement.id}/receipt`);
            imgElement.style.height = '100px';
            receipt.appendChild(imgElement);


            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tr.appendChild(receipt);


            tbody.appendChild(tr);
        }
    }
    
}


