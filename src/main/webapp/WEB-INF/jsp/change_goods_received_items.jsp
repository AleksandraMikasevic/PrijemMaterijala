<%-- 
    Document   : add_material
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br><br><br>
        <div class="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <form>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="kolicina">Kolicina</label>
                            <input type="text" name="kolicina" id = "kolicina" class="form-control"><br>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <select name="materijalID" id ="materijalID" class="form-control">
                                <c:forEach items="${listaMaterijala}" var="materijal">
                                    <option value="${materijal.sifraMaterijala}"><c:out value="${materijal.nazivMaterijala}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary" style="font-size:24px;" onclick="DodajStavku()"><i class="fa fa-plus-square-o"></i></button>
                </form>
            </div></div>
        <br><br>
        <div class ="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <table  id ="items" class="table table-hover table-striped table-bordered table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Redni broj</th>
                            <th scope="col">Kolicina</th>
                            <th scope="col">Sifra materijala</th>
                            <th scope="col">#</th>
                        </tr>
                    </thead>
                    <tbody id="stavke">
                        <c:forEach items="${grcn.stavkaprijemniceCollection}" var="item">
                            <tr>
                                <th scope="row">${item.redniBroj}</th>
                                <td>${item.kolicina}</td>
                                <td>${item.sifraMaterijala.sifraMaterijala}</td>
                                <td><a href="/NJProjekatFED/goods_received_note/change_goods_received_note_item_info/${item.stavkaprijemnicePK.brojStavke}" class="btn btn-primary"><i class="fa fa-cogs"></i></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <form action="/NJProjekatFED/goods_received_note/update_goods_received_note" method="POST">
                    <button type="sumbit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                </form>
            </div>
        </div>
    </div>
    <style>
        .ui-datepicker {
            background: black;
            color: #EEE;
        }
    </style>
    <script>
        var rbrStavkePrijemnice = 1;
        $(document).ready(function () {
            $("#datepicker").datepicker();
        });

        function DodajStavku() {
            var tbody = $("#items tbody");
            if (tbody.children().length !== 0) {
                rbrStavkePrijemnice = tbody.children().length + 1;
            }
            $.ajax({
                url: "/NJProjekatFED/goods_received_note/update_add_goods_received_note_item/" + rbrStavkePrijemnice + "/" + $("#materijalID").val() + "/" + $("#kolicina").val(),
                type: 'GET',
                dataType: 'html',
                success: function (data) {
                    $("#stavke").append(data);
                },
                error: function (jqxhr, status, exception) {
                    alert('Exception:', exception.toString());
                }
            });
        }

    </script>
</body>
