<%@ page import="java.util.List" %>
<%@ page import="com.greenswap.model.SwapItem" %>
<%@ page import="com.greenswap.util.WebText" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GreenSwap Listings</title>
    <style>
        :root { --bg:#fff; --bg-subtle:#f9fafb; --border:#e5e7eb; --text-main:#111827; --text-muted:#4b5563; --primary:#18181b; --accent:#2563eb; --success:#16a34a; --radius:10px; }
        *{box-sizing:border-box;margin:0;padding:0}
        body{font-family:Arial,sans-serif;background:var(--bg);color:var(--text-main);line-height:1.5}
        .wrap{max-width:1100px;margin:0 auto;padding:0 24px 40px}
        .topbar{display:flex;justify-content:space-between;align-items:center;padding:18px 0;border-bottom:1px solid var(--border)}
        .back{color:var(--text-muted);text-decoration:none;font-size:.85rem;font-weight:600;border:1px solid var(--border);border-radius:6px;padding:6px 12px}
        .hero{padding:38px 0 26px;border-bottom:1px solid var(--border);margin-bottom:24px}
        .tag{color:var(--accent);font-weight:700;text-transform:uppercase;letter-spacing:.08em;font-size:.75rem}
        h1{font-size:clamp(1.8rem,3vw,2.5rem);margin:8px 0 10px}
        .hero p{color:var(--text-muted);max-width:700px}
        .grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(280px,1fr));gap:16px}
        .item{border:1px solid var(--border);border-radius:var(--radius);background:#fff;padding:20px}
        .item-top{display:flex;justify-content:space-between;gap:10px;margin-bottom:10px}
        .item h2{font-size:1.1rem}
        .badge{font-size:.72rem;font-weight:700;color:var(--success);text-transform:uppercase;letter-spacing:.06em}
        .meta{font-size:.84rem;color:var(--text-muted);margin-bottom:8px}
        .desc{font-size:.9rem;color:var(--text-muted);margin-bottom:14px}
        .actions{display:flex;gap:8px;flex-wrap:wrap}
        .btn{padding:9px 12px;border-radius:8px;border:1px solid var(--border);background:#fff;text-decoration:none;color:var(--text-main);font-size:.85rem;font-weight:700;cursor:pointer}
        .btn.primary{background:var(--primary);border-color:var(--primary);color:#fff}
        .btn:hover{background:var(--bg-subtle)}
        .btn.primary:hover{background:#27272a}
        .empty{border:1px dashed var(--border);border-radius:var(--radius);padding:24px;text-align:center;color:var(--text-muted)}
    </style>
</head>
<body>
    <div class="wrap">
        <div class="topbar">
            <a class="back" href="index.jsp">Back Home</a>
            <div class="tag">Available Listings</div>
        </div>
        <section class="hero">
            <div class="tag">Browse and claim</div>
            <h1>GreenSwap item board</h1>
            <p>Open items show up here. A student can claim, and the status updates through JDBC and servlets.</p>
        </section>

        <%
            List<SwapItem> items = (List<SwapItem>) request.getAttribute("items");
            if (items == null || items.isEmpty()) {
        %>
            <div class="empty">No items posted yet. Add the first listing from the home page.</div>
        <%
            } else {
        %>
            <div class="grid">
        <%
                for (SwapItem item : items) {
        %>
                <article class="item">
                    <div class="item-top">
                        <h2><%= WebText.escapeHtml(item.getItemName()) %></h2>
                        <span class="badge"><%= WebText.escapeHtml(item.getStatus()) %></span>
                    </div>
                    <div class="meta"><strong>Category:</strong> <%= WebText.escapeHtml(item.getCategory()) %> | <strong>Condition:</strong> <%= WebText.escapeHtml(item.getItemCondition()) %></div>
                    <div class="desc"><%= WebText.escapeHtml(item.getDescription()) %></div>
                    <div class="meta"><strong>Contact:</strong> <%= WebText.escapeHtml(item.getContactEmail()) %></div>
                    <div class="actions">
                        <form method="post" action="claim-item" style="display:inline;">
                            <input type="hidden" name="id" value="<%= item.getId() %>" />
                            <button class="btn primary" type="submit" <%= "Claimed".equalsIgnoreCase(item.getStatus()) ? "disabled" : "" %>>Mark Claimed</button>
                        </form>
                        <form method="post" action="delete-item" style="display:inline;">
                            <input type="hidden" name="id" value="<%= item.getId() %>" />
                            <button class="btn" type="submit">Delete</button>
                        </form>
                    </div>
                </article>
        <%
                }
        %>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
