import { useState } from "react";

function DonorSearch() {
  const [bloodGroup, setBloodGroup] = useState("");
  const [location, setLocation] = useState("");
  const [donors, setDonors] = useState([]);

  const search = () => {
    fetch(`https://awake-appreciation-production.up.railway.app/donor/search?bloodGroup=${encodeURIComponent(bloodGroup)}&location=${encodeURIComponent(location)}`)
      .then(res => {
        if (!res.ok) throw new Error(`Backend Error ${res.status}`);
        return res.json();
      })
      .then(data => setDonors(data))
      .catch(err => {
        console.error("Search failed:", err);
        alert("Search Failed: " + err.message);
      });
  };

  // 🔥 NEW: Send Blood Request
  const requestBlood = (donor) => {
    const requestData = {
      patientName: "Emergency Patient",
      bloodGroup: donor.bloodGroup,
      hospital: "City Hospital",
      location: donor.location,
      status: "PENDING"
    };

    fetch("https://awake-appreciation-production.up.railway.app/request/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    })
      .then(res => res.json())
      .then(() => alert("Request Sent Successfully ✅"))
      .catch(() => alert("Error sending request ❌"));
  };

  return (
    <div>
      <h3>Search Donor</h3>

      <input placeholder="🩸 Blood Group" onChange={(e) => setBloodGroup(e.target.value)} />
      <input placeholder="📍 Location" onChange={(e) => setLocation(e.target.value)} />

      <button onClick={search}>Search</button>

      {donors.map((d) => (
        <div className="donor-card" key={d.id}>
          <h3>{d.name}</h3>
          <p>🩸 {d.bloodGroup}</p>
          <p>📍 {d.location}</p>
          <p>📞 {d.phone}</p>

          <a href={`tel:${d.phone}`}>
            <button>Call Now</button>
          </a>

          {/* 🔥 NEW BUTTON */}
          <button onClick={() => requestBlood(d)}>
            Request Blood
          </button>
        </div>
      ))}
    </div>
  );
}

export default DonorSearch;