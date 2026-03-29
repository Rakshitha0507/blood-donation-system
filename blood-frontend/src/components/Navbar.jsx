function Navbar({ setPage }) {
  return (
    <div className="navbar">
      <h2>❤️ Blood Donation</h2>

      <div className="nav-buttons">
        <button onClick={() => setPage("home")}>🏠 Home</button>
        <button onClick={() => setPage("register")}>➕ Register</button>
        <button onClick={() => setPage("login")}>🔐 Login</button>
        <button onClick={() => setPage("request")}>🩸 Request</button>
        <button onClick={() => setPage("search")}>🔍 Search</button>
        <button onClick={() => setPage("requests")}>📋 Requests</button>
      </div>
    </div>
  );
}

export default Navbar;