<%@ page contentType="text/html;charset=UTF-8" %>
<%! 
    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int divisor = 2; divisor <= number / divisor; divisor++) {
            if (number % divisor == 0) return false;
        }
        return true;
    }

    private long sumPrimesBetween(int first, int second) {
        int lower = Math.min(first, second);
        int upper = Math.max(first, second);
        long sum = 0;
        for (int number = lower + 1; number < upper; number++) {
            if (isPrime(number)) sum += number;
        }
        return sum;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sum of Prime Numbers</title>
</head>
<body>
    <h1>Sum of Prime Numbers Between Two Numbers</h1>
    <form method="get" action="prime.jsp">
        <label for="first">First number (m)</label>
        <input id="first" name="m" type="number" required>
        <label for="second">Second number (n)</label>
        <input id="second" name="n" type="number" required>
        <button type="submit">Calculate Sum</button>
    </form>

    <%
        String firstText = request.getParameter("m");
        String secondText = request.getParameter("n");
        if (firstText != null && secondText != null) {
            try {
                int first = Integer.parseInt(firstText);
                int second = Integer.parseInt(secondText);
    %>
                <h2>Result</h2>
                <p>The sum of prime numbers strictly between <%= first %> and <%= second %> is
                    <strong><%= sumPrimesBetween(first, second) %></strong>.</p>
    <%
            } catch (NumberFormatException exception) {
    %>
                <p role="alert">Please enter valid whole numbers.</p>
    <%
            }
        }
    %>
</body>
</html>