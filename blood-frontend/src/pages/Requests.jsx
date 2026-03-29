import { useEffect, useState } from "react";

function Requests() {
  const [requests, setRequests] = useState([]);

  const fetchRequests = () => {
    fetch("http://localhost:8081/request/all")
      .then(res => res.json())
      .then(data => setRequests(data))
      .catch(() => alert("Error fetching requests"));
  };

  useEffect(() => {
    fetchRequests();
  }, []);

  // 🔥 Update Status
  const updateStatus = (id, status) => {
    fetch(`http://localhost:8081/request/update-status/${id}?status=${status}`, {
      method: "PUT"
    })
      .then(res => res.json())
      .then(() => {
        alert("Status Updated ✅");
        fetchRequests(); // refresh
      })
      .catch(() => alert("Error updating status"));
  };

  // 🎨 Status Color
  const getColor = (status) => {
    if (status === "PENDING") return "orange";
    if (status === "ACCEPTED") return "green";
    if (status === "REJECTED") return "red";
    return "black";
  };

  return (
    <div>
      <h2>Blood Requests</h2>

      {requests.map((r) => (
        <div key={r.id} className="request-card">
          <h3>{r.patientName}</h3>
          <p>🩸 {r.bloodGroup}</p>
          <p>🏥 {r.hospital}</p>
          <p>📍 {r.location}</p>

          <p style={{ color: getColor(r.status) }}>
            Status: {r.status}
          </p>

          {/* 🔥 ACTION BUTTONS */}
          <button onClick={() => updateStatus(r.id, "ACCEPTED")}>
            Accept
          </button>

          <button onClick={() => updateStatus(r.id, "REJECTED")}>
            Reject
          </button>
        </div>
      ))}
    </div>
  );
}

export default Requests;