import { useState } from "react";

function Register() {
  const [data, setData] = useState({});
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    fetch("https://awake-appreciation-production.up.railway.app/donor/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })
      .then(res => {
        if (!res.ok) throw new Error("Server error");
        return res.json();
      })
      .then(() => {
        setMessage("Donor Registered Successfully ✅");
      })
      .catch(err => {
        setMessage("❌ " + err.message);
      });
  };

  return (
    <div>
      <h3>Register Donor</h3>

      <input name="name" placeholder="👤 Name" onChange={handleChange} />
      <input name="bloodGroup" placeholder="🩸 Blood Group" onChange={handleChange} />
      <input name="location" placeholder="📍 Location" onChange={handleChange} />
      <input name="phone" placeholder="📞 Phone" onChange={handleChange} />
      <input name="email" placeholder="📧 Email" onChange={handleChange} />

      {/* 🔥 NEW */}
      <input name="username" placeholder="👤 Username" onChange={handleChange} />
      <input type="password" name="password" placeholder="🔒 Password" onChange={handleChange} />

      <button onClick={handleSubmit}>Register</button>

      {message && <p>{message}</p>}
    </div>
  );
}

export default Register;