let currentPage = 0;
const pageSize = 3;

function clearMainContent() {
  document.getElementById('mainContent').innerHTML = '';
}

async function loadView(page = 0) {
  clearMainContent();
  try {
    let res = await fetch(`/policies/policies?page=${page}&size=${pageSize}`);
    if (!res.ok) {
      document.getElementById('mainContent').innerHTML = 'No policies found.';
      return;
    }

    let data = await res.json();
    let policies = data.content;

    if (!policies || policies.length === 0) {
      document.getElementById('mainContent').innerHTML = 'No policies found.';
      return;
    }

    let html = '<table border="1" cellspacing="0" cellpadding="5"><tr><th>ID</th><th>Name</th><th>Vehicle</th><th>Status</th></tr>';

    for (let p of policies) {
      html += `<tr>
                 <td>${p.policyId}</td>
                 <td>${p.policyname}</td>
                 <td>${p.vehicleType}</td>
                 <td>${p.status}</td>
               </tr>`;
    }
    html += '</table>';

    html += `<div style="margin-top:10px;">
               <button ${data.first ? 'disabled' : ''} onclick="loadView(${page - 1})">Previous</button>
               <button ${data.last ? 'disabled' : ''} onclick="loadView(${page + 1})">Next</button>
             </div>`;

    document.getElementById('mainContent').innerHTML = html;
    currentPage = page;

  } catch (err) {
    document.getElementById('mainContent').innerHTML = 'Error loading policies.';
    console.error(err);
  }
}

function loadSearch() {
  clearMainContent();
  document.getElementById('mainContent').innerHTML = `
    <h3>Search Policy</h3>
    <input id="searchPolicyId" placeholder="Enter Policy ID" />
    <button onclick="searchPolicy()">Search</button>
    <div id="searchResult" style="margin-top:10px;"></div> `;
}

async function searchPolicy() {
  let id = document.getElementById('searchPolicyId').value.trim();
  let resultDiv = document.getElementById('searchResult');

  if (!id) {
    resultDiv.innerHTML = 'Please enter a valid Policy ID.';
    return;
  }

  try {
    let res = await fetch(`/policies/${id}`);
    if (!res.ok) {
      resultDiv.innerHTML = `No policy found with ID: ${id}`;
      return;
    }
    let p = await res.json();

    resultDiv.innerHTML = `
      <b>ID:</b> ${p.policyId} <br>
      <b>Name:</b> ${p.policyname} <br>
      <b>Vehicle Type:</b> ${p.vehicleType} <br>
      <b>Status:</b> ${p.status} <br>
      <b>Year:</b> ${p.year} <br>
      <b>Month:</b> ${p.month} <br>
      <b>Premium:</b> ${p.premiumamount} <br>
      <b>Tenure (months):</b> ${p.tenureInMonths} <br>
      <b>Description:</b> ${p.description} `;
  } catch {
    resultDiv.innerHTML = 'Error fetching policy.';
  }
}

function loadUpdate() {
  clearMainContent();
  document.getElementById('mainContent').innerHTML = `
    <h3>Update Policy</h3>
    <input id="updatePolicyId" placeholder="Enter Policy ID to update" />
    <button onclick="fetchPolicyForUpdate()">Fetch Policy</button>
    <div id="updateFormContainer" style="margin-top:10px;"></div> `;
}

async function fetchPolicyForUpdate() {
  let id = document.getElementById('updatePolicyId').value.trim();
  let container = document.getElementById('updateFormContainer');

  if (!id) {
    alert('Please enter a valid Policy ID');
    return;
  }

  try {
    let res = await fetch(`/policies/${id}`);
    if (!res.ok) {
      container.innerHTML = `No policy found with ID: ${id}`;
      return;
    }
    let p = await res.json();

    container.innerHTML = `
      <form id="updatePolicyForm">
        Name:<br><input name="policyname" value="${p.policyname}" required /><br><br>
        Vehicle Type:<br><input name="vehicleType" value="${p.vehicleType}" required /><br><br>
        Status:<br><input name="status" value="${p.status}" required /><br><br>
        Premium Amount:<br><input type="number" name="premiumamount" value="${p.premiumamount}" required /><br><br>
        Tenure (Months):<br><input type="number" name="tenureInMonths" value="${p.tenureInMonths}" required /><br><br>
        Description:<br><textarea name="description">${p.description}</textarea><br><br>
        <button type="submit">Update Policy</button>
      </form>
      <p id="updateMessage" style="color:green;"></p> `;

    document.getElementById('updatePolicyForm').onsubmit = async function(e) {
      e.preventDefault();
      let formData = new FormData(this);
      let updated = {};
      formData.forEach((v, k) => {
        updated[k] = (k === 'premiumamount' || k === 'tenureInMonths') ? Number(v) : v;
      });

      let res = await fetch(`/policies/${id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(updated)
      });

      let msg = document.getElementById('updateMessage');
      if (res.ok) {
        msg.textContent = 'Policy updated successfully!';
        this.style.display = 'none';
      } else {
        msg.textContent = 'Failed to update policy.';
      }
    };

  } catch {
    container.innerHTML = 'Error fetching policy.';
  }
}

function loadDelete() {
  clearMainContent();
  document.getElementById('mainContent').innerHTML = `
    <h3>Delete Policy</h3>
    <input id="deletePolicyId" placeholder="Enter Policy ID to delete" />
    <button onclick="deleteByInput()">Delete</button> `;
}

function deleteByInput() {
  let id = document.getElementById('deletePolicyId').value.trim();
  if (!id) {
    alert('Please enter a valid Policy ID');
    return;
  }
  showDeleteModal(id);
}

// Modal logic without fancy stuff
document.addEventListener('DOMContentLoaded', function() {
  let modal = document.getElementById('deleteModal');

  window.showDeleteModal = function(policyId) {
    document.getElementById('modalPolicyId').textContent = policyId;
    modal.style.display = 'block';
    modal.style.backgroundColor = 'rgba(0,0,0,0.5)';
  };

  window.closeModal = function() {
    modal.style.display = 'none';
  };

  document.getElementById('confirmDeleteBtn').onclick = function() {
    let id = document.getElementById('modalPolicyId').textContent;

    fetch(`/policies/${id}`, {method: 'DELETE'})
      .then(res => {
        if (res.ok) {
          alert(`Policy with ID ${id} deleted.`);
          closeModal();
          loadView(currentPage);
        } else {
          alert('Failed to delete policy.');
        }
      })
      .catch(() => alert('Error deleting policy.'));
  };

  // Close modal if clicked outside content
  modal.onclick = function(e) {
    if (e.target === modal) {
      closeModal();
    }
  };
});
