import { useState } from "react";

function Request() {
  const [data, setData] = useState({});
  const [message, setMessage] = useState("");

  const token = localStorage.getItem("token");

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {

    if (!token) {
      setMessage("❌ Please login first");
      return;
    }

    fetch("http://localhost:8081/request/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token // 🔥 IMPORTANT
      },
      body: JSON.stringify(data),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Failed");
        return res.json();
      })
      .then(() => {
        setMessage("Request sent successfully ✅");
      })
      .catch(() => {
        setMessage("❌ Failed to send request");
      });
  };

  return (
    <div>
      <h3>Blood Request</h3>

      <input name="patientName" placeholder="Patient Name" onChange={handleChange} />
      <input name="bloodGroup" placeholder="Blood Group" onChange={handleChange} />
      <input name="hospital" placeholder="Hospital" onChange={handleChange} />
      <input name="location" placeholder="Location" onChange={handleChange} />

      <button onClick={handleSubmit}>Submit</button>

      {message && <p>{message}</p>}
    </div>
  );
}

export default Request;