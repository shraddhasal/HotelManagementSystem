<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<aside class="filter-sidebar">

  <h3 class="filter-title">Filter By</h3>

  <!-- ROOM TYPE -->
  <div class="filter-group">
    <h4>Room Type</h4>
    <div class="type-buttons">
      <button class="active">All</button>
      <button>Deluxe</button>
      <button>Superior</button>
      <button>Suite</button>
      <button>Family</button>
    </div>
  </div>

  <!-- PRICE RANGE -->
  <div class="filter-group">
    <h4>Price Range</h4>
    <input type="range" min="1000" max="10000" step="500" value="10000">
    <p class="price-text">Up to ₹10000</p>
  </div>

  <!-- GUESTS -->
  <div class="filter-group">
    <h4>Guests</h4>

    <label class="checkbox">
      <input type="radio" name="guests"> 1 Adult
    </label>

    <label class="checkbox">
      <input type="radio" name="guests"> 2 Adults
    </label>

    <label class="checkbox">
      <input type="radio" name="guests"> Family (4+)
    </label>
  </div>

  <!-- AMENITIES -->
  <div class="filter-group">
    <h4>Amenities</h4>

    <label class="checkbox">
      <input type="checkbox"> WiFi
    </label>

    <label class="checkbox">
      <input type="checkbox"> Breakfast
    </label>

    <label class="checkbox">
      <input type="checkbox"> AC
    </label>

    <label class="checkbox">
      <input type="checkbox"> Balcony
    </label>

    <label class="checkbox">
      <input type="checkbox"> Pool
    </label>
  </div>

</aside>

</body>
</html>