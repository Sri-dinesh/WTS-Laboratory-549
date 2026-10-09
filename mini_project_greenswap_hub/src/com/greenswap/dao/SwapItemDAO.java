package com.greenswap.dao;

import com.greenswap.model.SwapItem;
import com.greenswap.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SwapItemDAO {

    public boolean itemExists(int id) throws SQLException {
        String query = "SELECT 1 FROM swap_items WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void addItem(SwapItem item) throws SQLException {
        String query = "INSERT INTO swap_items(item_name, category, description, item_condition, contact_email, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getCategory());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getItemCondition());
            ps.setString(5, item.getContactEmail());
            ps.setString(6, item.getStatus());
            ps.executeUpdate();
        }
    }

    public List<SwapItem> findAll() throws SQLException {
        String query = "SELECT id, item_name, category, description, item_condition, contact_email, status, created_at FROM swap_items ORDER BY id DESC";
        List<SwapItem> items = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SwapItem item = new SwapItem();
                item.setId(rs.getInt("id"));
                item.setItemName(rs.getString("item_name"));
                item.setCategory(rs.getString("category"));
                item.setDescription(rs.getString("description"));
                item.setItemCondition(rs.getString("item_condition"));
                item.setContactEmail(rs.getString("contact_email"));
                item.setStatus(rs.getString("status"));
                item.setCreatedAt(rs.getString("created_at"));
                items.add(item);
            }
        }

        return items;
    }

    public boolean markClaimed(int id) throws SQLException {
        String query = "UPDATE swap_items SET status = 'Claimed' WHERE id = ? AND status <> 'Claimed'";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(int id) throws SQLException {
        String query = "DELETE FROM swap_items WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
