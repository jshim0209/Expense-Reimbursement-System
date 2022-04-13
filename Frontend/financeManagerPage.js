let logoutBtn = document.querySelector('#logout-btn');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('user_role');
    localStorage.removeItem('user_id');
    localStorage.removeItem('jwt');


    window.location.href = './index.html';

});


window.addEventListener('load', (event) => {
    populateReimbursementsTable();
});

async function populateReimbursementsTable() {
    const URL = 'http://localhost:8081/reimbursements';


    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            
        }
    })


    if (res.status === 200) {
        let reimbursements = await res.json();

        let tbody = document.querySelector('#manager-reimbursements-tbl > tbody');
        tbody.innerHTML = ''; // clear out the tbody

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
            if(reimbursement.status == "approved"){
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


async function populateDeniedReimbursementsTable() {
    const URL = 'http://localhost:8081/reimbursements/denied';


    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            
        }
    })


    if (res.status === 200) {
        let reimbursements = await res.json();

        let tbody = document.querySelector('#manager-reimbursements-tbl > tbody');
        tbody.innerHTML = ''; // clear out the tbody

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
            if(reimbursement.status == "approved"){
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


async function populateApprovedReimbursementsTable() {
    const URL = 'http://localhost:8081/reimbursements/approved';
    console.log('hi');


    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            
        }
    })


    if (res.status === 200) {
        let reimbursements = await res.json();

        let tbody = document.querySelector('#manager-reimbursements-tbl > tbody');
        tbody.innerHTML = ''; // clear out the tbody

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
            if(reimbursement.status == "approved"){
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


async function populatePendingReimbursementsTable() {
    const URL = 'http://localhost:8081/reimbursements/pending';


    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            
        }
    })


    if (res.status === 200) {
        let reimbursements = await res.json();

        let tbody = document.querySelector('#manager-reimbursements-tbl > tbody');
        tbody.innerHTML = ''; // clear out the tbody

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
            if(reimbursement.status == "approved"){
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


async function approvePendingReimbursements() {
    const URL = 'http://localhost:8081/reimbursements/pending';


    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            
        }
    })


    if (res.status === 200) {
        let reimbursements = await res.json();

        let tbody = document.querySelector('#manager-reimbursements-tbl > tbody');
        tbody.innerHTML = ''; // clear out the tbody

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
            if(reimbursement.status == "approved"){
                td7.innerText = "Approved";
            }
            if(reimbursement.status == "pending"){
                td7.innerText = "Pending";
            }
            if(reimbursement.status == "denied"){
                td7.innerText = "Rejected";
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


            let statusSelection = document.createElement('select');

            // option
            let option = document.createElement('option');
            option.innerHTML = '';

            // approved option
            let approvedOption = document.createElement('option');
            approvedOption.setAttribute('value', '2');
            approvedOption.innerHTML = 'Approve';
        
            // denied option
            let deniedOption = document.createElement('option');
            deniedOption.setAttribute('value', '3');
            deniedOption.innerHTML = 'Reject';

            statusSelection.appendChild(option);
            statusSelection.appendChild(approvedOption);
            statusSelection.appendChild(deniedOption);

            // submit button
            let statusBtn = document.createElement('button');
            statusBtn.innerText = 'Submit Change';
            statusBtn.addEventListener('click', async () => {
        
            let res = await fetch(`http://localhost:8081/reimbursements/${reimbursement.id}`, {
                    method: 'PATCH',
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                    },
                    body: JSON.stringify({
                        statusId: statusSelection.value
                    })
                });
                        
                if (res.status === 201) {
                    approvePendingReimbursements(); 
                } else if (res.status === 401) {
                    let reimbForm = document.querySelector('.message');
            
                    let data = await res.json();
            
                    let pTag = document.createElement('p');
                    pTag.innerHTML = data.message; 
                    pTag.style.color = 'red';
            
                    reimbForm.appendChild(pTag);
                }
            });

            

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(statusSelection);
            tr.appendChild(td8);
            tr.appendChild(receipt);
            tr.appendChild(statusBtn);



            tbody.appendChild(tr);

        }
    }

}




