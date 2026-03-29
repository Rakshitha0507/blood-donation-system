import { useState } from "react";
import "./App.css";
import Register from "./pages/Register";
import Request from "./pages/Request";
import DonorSearch from "./pages/DonorSearch";
import Login from "./pages/Login";

function App() {
  const [page, setPage] = useState("home");

  // Auth state
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [username, setUsername] = useState(localStorage.getItem("username"));

  const handleLogout = () => {
    localStorage.clear();
    setToken(null);
    setUsername(null);
    setPage("home");
  };

  return (
    <div>

      {/* Navbar */}
      <div className="navbar">
        <h2>❤️ Blood Donation</h2>

        <div className="nav-buttons">

          <button onClick={() => setPage("home")}>Home</button>

          {!token && (
            <>
              <button onClick={() => setPage("register")}>Register</button>
              <button onClick={() => setPage("login")}>Login</button>
            </>
          )}

          {token && (
            <>
              <button onClick={() => setPage("request")}>Request</button>
              <button onClick={() => setPage("search")}>Search</button>

              <span style={{ color: "white", marginLeft: "10px" }}>
                👤 {username}
              </span>

              <button onClick={handleLogout}>Logout</button>
            </>
          )}

        </div>
      </div>

      {/* Content */}
      <div className="container">

        {page === "home" && (
          <div className="home">
            <h1>❤️ Save Lives, Donate Blood</h1>

            {/* ✅ IMAGE FIXED HERE */}
            <img
              src="https://cdn-icons-png.flaticon.com/512/3774/3774299.png"
              className="home-img"
              alt="blood"
            />

            <p>Every drop counts. Be someone's hero today.</p>

            {/* 🔥 Optional CTA buttons */}
            <div style={{ marginTop: "20px" }}>
              {!token && (
                <>
                  <button onClick={() => setPage("register")}>Become Donor</button>
                  <button onClick={() => setPage("login")}>Login</button>
                </>
              )}

              {token && (
                <>
                  <button onClick={() => setPage("request")}>Request Blood</button>
                  <button onClick={() => setPage("search")}>Find Donor</button>
                </>
              )}
            </div>

          </div>
        )}

        {page === "register" && (
          <div className="card">
            <Register />
          </div>
        )}

        {page === "login" && (
          <div className="card">
            <Login
              setToken={setToken}
              setUsername={setUsername}
              setPage={setPage}
            />
          </div>
        )}

        {page === "request" && (
          <div className="card">
            <Request />
          </div>
        )}

        {page === "search" && (
          <div className="card">
            <DonorSearch />
          </div>
        )}

      </div>

      {/* Footer */}
      <div className="footer">
        <p>© 2026 Blood Donation System ❤️</p>
      </div>

    </div>
  );
}

export default App;