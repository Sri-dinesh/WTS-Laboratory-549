<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Post an Item</title>
    <style>
      :root {
        --bg: #fff;
        --bg-subtle: #f9fafb;
        --border: #e5e7eb;
        --text-main: #111827;
        --text-muted: #4b5563;
        --primary: #18181b;
        --accent: #2563eb;
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
        max-width: 760px;
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
      .back {
        color: var(--text-muted);
        text-decoration: none;
        font-size: 0.85rem;
        font-weight: 600;
        border: 1px solid var(--border);
        border-radius: 6px;
        padding: 6px 12px;
      }
      .hero {
        padding: 38px 0 26px;
        border-bottom: 1px solid var(--border);
        margin-bottom: 24px;
      }
      .tag {
        color: var(--accent);
        font-weight: 700;
        text-transform: uppercase;
        letter-spacing: 0.08em;
        font-size: 0.75rem;
      }
      h1 {
        font-size: clamp(1.8rem, 3vw, 2.5rem);
        margin: 8px 0 10px;
      }
      .hero p {
        color: var(--text-muted);
        max-width: 680px;
      }
      .card {
        border: 1px solid var(--border);
        border-radius: var(--radius);
        background: #fff;
        padding: 24px;
      }
      .field {
        margin-bottom: 16px;
      }
      label {
        display: block;
        font-weight: 700;
        font-size: 0.85rem;
        margin-bottom: 6px;
      }
      input,
      textarea,
      select {
        width: 100%;
        padding: 11px 14px;
        border: 1px solid var(--border);
        border-radius: 8px;
        font: inherit;
      }
      textarea {
        min-height: 120px;
        resize: vertical;
      }
      input:focus,
      textarea:focus,
      select:focus {
        outline: none;
        border-color: var(--primary);
        box-shadow: 0 0 0 2px rgba(24, 24, 27, 0.08);
      }
      .btn-row {
        display: flex;
        gap: 10px;
        flex-wrap: wrap;
        margin-top: 18px;
      }
      .btn {
        padding: 10px 14px;
        border-radius: 8px;
        border: 1px solid var(--border);
        background: #fff;
        text-decoration: none;
        color: var(--text-main);
        font-weight: 700;
        cursor: pointer;
      }
      .btn.primary {
        background: var(--primary);
        border-color: var(--primary);
        color: #fff;
      }
      .btn:hover {
        background: var(--bg-subtle);
      }
      .btn.primary:hover {
        background: #27272a;
      }
    </style>
  </head>
  <body>
    <div class="wrap">
      <div class="topbar">
        <a class="back" href="index.jsp">Back Home</a>
        <div class="tag">Post Listing</div>
      </div>
      <section class="hero">
        <div class="tag">Share what you do not need</div>
        <h1>Add a GreenSwap item</h1>
        <p>
          Post a book, gadget, or daily-use item for free pickup or exchange.
          This page sends data to the JDBC-backed servlet.
        </p>
      </section>
      <section class="card">
        <form method="post" action="add-item">
          <div class="field">
            <label for="itemName">Item Name</label
            ><input
              id="itemName"
              name="itemName"
              type="text"
              placeholder="Eg. Java programming book"
              required
            />
          </div>
          <div class="field">
            <label for="category">Category</label
            ><input
              id="category"
              name="category"
              type="text"
              placeholder="Eg. Books, Gadgets, Stationery"
              required
            />
          </div>
          <div class="field">
            <label for="description">Description</label
            ><textarea
              id="description"
              name="description"
              placeholder="Short details, swap idea, pickup notes"
              required
            ></textarea>
          </div>
          <div class="field">
            <label for="itemCondition">Condition</label
            ><select id="itemCondition" name="itemCondition">
              <option>Like New</option>
              <option>Good</option>
              <option>Usable</option>
              <option>Needs Repair</option>
            </select>
          </div>
          <div class="field">
            <label for="contactEmail">Contact Email</label
            ><input
              id="contactEmail"
              name="contactEmail"
              type="email"
              placeholder="name@college.edu"
              required
            />
          </div>
          <div class="btn-row">
            <button class="btn primary" type="submit">Save Item</button>
            <a class="btn" href="list-items">Browse Items</a>
          </div>
        </form>
      </section>
    </div>
  </body>
</html>
