package pro.cleancoder.books.port.out;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

interface BooksInsertRepository {

    @SqlUpdate("insert into books (uuid, author, title, gender) values (:uuid, :author, :title, :gender)")
    int insert(@BindBean BookEntity book);

}
