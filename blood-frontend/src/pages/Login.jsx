import { useState } from "react";

function Login({ setToken, setUsername, setPage }) {
  const [data, setData] = useState({});
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    fetch("http://localhost:8081/donor/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Invalid credentials");
        return res.json();
      })
      .then((data) => {
        localStorage.setItem("token", data.token);
        localStorage.setItem("username", data.username || "User");

        setToken(data.token);
        setUsername(data.username || "User");

        setMessage("Login successful ✅");
        setPage("home");
      })
      .catch((err) => {
        setMessage("❌ " + err.message);
      });
  };

  return (
    <div>
      <h3>Login</h3>

      <input
        name="username"
        placeholder="👤 Username"
        onChange={handleChange}
      />

      <input
        type="password"
        name="password"
        placeholder="🔒 Password"
        onChange={handleChange}
      />

      <button onClick={handleSubmit}>Login</button>

      {message && <p>{message}</p>}
    </div>
  );
}

export default Login;