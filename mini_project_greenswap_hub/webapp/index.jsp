<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Campus GreenSwap Hub</title>
    <style>
      :root {
        --bg: #ffffff;
        --bg-subtle: #f9fafb;
        --border: #e5e7eb;
        --text-main: #111827;
        --text-muted: #4b5563;
        --primary: #18181b;
        --accent: #2563eb;
        --success: #16a34a;
        --radius: 10px;
      }

      * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
      }
      body {
        font-family: Arial, sans-serif;
        background: var(--bg);
        color: var(--text-main);
        line-height: 1.5;
      }
      .wrap {
        max-width: 1100px;
        margin: 0 auto;
        padding: 0 24px 40px;
      }
      .topbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 18px 0;
        border-bottom: 1px solid var(--border);
      }
      .pill {
        font-size: 0.8rem;
        color: var(--text-muted);
      }
      .hero {
        padding: 42px 0 30px;
        border-bottom: 1px solid var(--border);
        margin-bottom: 28px;
      }
      .tag {
        color: var(--accent);
        font-weight: 700;
        text-transform: uppercase;
        letter-spacing: 0.08em;
        font-size: 0.75rem;
      }
      h1 {
        font-size: clamp(1.9rem, 3vw, 2.8rem);
        margin: 8px 0 10px;
      }
      .hero p {
        max-width: 760px;
        color: var(--text-muted);
      }
      .grid {
        display: grid;
        grid-template-columns: 1.15fr 0.85fr;
        gap: 18px;
      }
      .card {
        border: 1px solid var(--border);
        border-radius: var(--radius);
        background: #fff;
        padding: 24px;
      }
      .card h2 {
        font-size: 1rem;
        margin-bottom: 14px;
      }
      .feature-list {
        display: grid;
        gap: 10px;
        list-style: none;
      }
      .feature-list li {
        background: var(--bg-subtle);
        border: 1px solid var(--border);
        border-radius: 8px;
        padding: 12px 14px;
        color: var(--text-muted);
      }
      .stats {
        display: grid;
        gap: 12px;
      }
      .stat {
        background: var(--bg-subtle);
        border: 1px solid var(--border);
        border-radius: 8px;
        padding: 14px;
      }
      .stat strong {
        display: block;
        font-size: 1.15rem;
        margin-bottom: 4px;
      }
      .btn-row {
        display: flex;
        gap: 10px;
        flex-wrap: wrap;
        margin-top: 18px;
      }
      .btn {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        padding: 10px 14px;
        border-radius: 8px;
        border: 1px solid var(--border);
        text-decoration: none;
        color: var(--text-main);
        font-weight: 600;
      }
      .btn.primary {
        background: var(--primary);
        color: #fff;
        border-color: var(--primary);
      }
      .btn:hover {
        background: var(--bg-subtle);
      }
      .btn.primary:hover {
        background: #27272a;
      }
      @media (max-width: 860px) {
        .grid {
          grid-template-columns: 1fr;
        }
        .topbar {
          flex-direction: column;
          align-items: flex-start;
          gap: 8px;
        }
      }
    </style>
  </head>
  <body>
    <div class="wrap">
      <div class="topbar">
        <div class="tag">Mini Project 2026</div>
        <div class="pill">JDBC + Servlets + JSP</div>
      </div>

      <section class="hero">
        <div class="tag">Campus Sustainability</div>
        <h1>Campus GreenSwap Hub</h1>
        <p>
          A mini project where students can post unused books, gadgets, and
          essentials for free pickup or exchange. It solves waste, helps
          budget-conscious students, and gives you a neat JDBC-servlet-JSP demo
          with CRUD operations.
        </p>
        <div class="btn-row">
          <a class="btn primary" href="add-item.jsp">Post an Item</a>
          <a class="btn" href="list-items">Browse Listings</a>
        </div>
      </section>

      <div class="grid">
        <section class="card">
          <h2>USP</h2>
          <ul class="feature-list">
            <li>Turns unused student items into reusable campus resources.</li>
            <li>Simple claim workflow keeps the project easy to demo.</li>
            <li>Built with classic Java web stack: JDBC, Servlet, JSP.</li>
          </ul>
        </section>

        <section class="card">
          <h2>Quick Stats</h2>
          <div class="stats">
            <div class="stat">
              <strong>Problem</strong
              ><span>Student waste and duplicate purchases</span>
            </div>
            <div class="stat">
              <strong>Solution</strong
              ><span>List, claim, update status, and delete items</span>
            </div>
            <div class="stat">
              <strong>Database</strong><span>MariaDB / MySQL using JDBC</span>
            </div>
          </div>
        </section>
      </div>
    </div>
  </body>
</html>
