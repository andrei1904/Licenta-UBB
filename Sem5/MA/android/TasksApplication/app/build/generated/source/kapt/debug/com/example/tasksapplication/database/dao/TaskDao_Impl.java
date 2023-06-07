package com.example.tasksapplication.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.tasksapplication.model.Priority;
import com.example.tasksapplication.model.Status;
import com.example.tasksapplication.model.Task;
import io.reactivex.Observable;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Task> __insertionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter<Task> __updateAdapterOfTask;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TaskDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTask = new EntityInsertionAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tasks` (`id`,`domain`,`title`,`description`,`createdTime`,`priority`,`deadline`,`status`,`progress`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        stmt.bindLong(1, value.getId());
        if (value.getDomain() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDomain());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        stmt.bindLong(5, value.getCreatedTime());
        if (value.getPriority() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, __Priority_enumToString(value.getPriority()));
        }
        stmt.bindLong(7, value.getDeadline());
        if (value.getStatus() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, __Status_enumToString(value.getStatus()));
        }
        stmt.bindLong(9, value.getProgress());
      }
    };
    this.__updateAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`domain` = ?,`title` = ?,`description` = ?,`createdTime` = ?,`priority` = ?,`deadline` = ?,`status` = ?,`progress` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        stmt.bindLong(1, value.getId());
        if (value.getDomain() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDomain());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        stmt.bindLong(5, value.getCreatedTime());
        if (value.getPriority() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, __Priority_enumToString(value.getPriority()));
        }
        stmt.bindLong(7, value.getDeadline());
        if (value.getStatus() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, __Status_enumToString(value.getStatus()));
        }
        stmt.bindLong(9, value.getProgress());
        stmt.bindLong(10, value.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tasks WHERE id =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tasks";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Task task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTask.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Task> tasks) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTask.insert(tasks);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Task task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final int taskId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, taskId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public Observable<List<Task>> getAll() {
    final String _sql = "SELECT * FROM tasks ORDER BY title ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createObservable(__db, false, new String[]{"tasks"}, new Callable<List<Task>>() {
      @Override
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDomain = CursorUtil.getColumnIndexOrThrow(_cursor, "domain");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCreatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createdTime");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Task _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDomain;
            if (_cursor.isNull(_cursorIndexOfDomain)) {
              _tmpDomain = null;
            } else {
              _tmpDomain = _cursor.getString(_cursorIndexOfDomain);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpCreatedTime;
            _tmpCreatedTime = _cursor.getLong(_cursorIndexOfCreatedTime);
            final Priority _tmpPriority;
            _tmpPriority = __Priority_stringToEnum(_cursor.getString(_cursorIndexOfPriority));
            final long _tmpDeadline;
            _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            final Status _tmpStatus;
            _tmpStatus = __Status_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final int _tmpProgress;
            _tmpProgress = _cursor.getInt(_cursorIndexOfProgress);
            _item = new Task(_tmpId,_tmpDomain,_tmpTitle,_tmpDescription,_tmpCreatedTime,_tmpPriority,_tmpDeadline,_tmpStatus,_tmpProgress);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Task> getById(final int taskId) {
    final String _sql = "SELECT * FROM tasks WHERE id =? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, taskId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tasks"}, false, new Callable<Task>() {
      @Override
      public Task call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDomain = CursorUtil.getColumnIndexOrThrow(_cursor, "domain");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCreatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createdTime");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final Task _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDomain;
            if (_cursor.isNull(_cursorIndexOfDomain)) {
              _tmpDomain = null;
            } else {
              _tmpDomain = _cursor.getString(_cursorIndexOfDomain);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpCreatedTime;
            _tmpCreatedTime = _cursor.getLong(_cursorIndexOfCreatedTime);
            final Priority _tmpPriority;
            _tmpPriority = __Priority_stringToEnum(_cursor.getString(_cursorIndexOfPriority));
            final long _tmpDeadline;
            _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            final Status _tmpStatus;
            _tmpStatus = __Status_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final int _tmpProgress;
            _tmpProgress = _cursor.getInt(_cursorIndexOfProgress);
            _result = new Task(_tmpId,_tmpDomain,_tmpTitle,_tmpDescription,_tmpCreatedTime,_tmpPriority,_tmpDeadline,_tmpStatus,_tmpProgress);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public void deleteIfNotPresent(final List<Integer> tasksIds) {
    __db.assertNotSuspendingTransaction();
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("DELETE FROM tasks WHERE id NOT IN (");
    final int _inputSize = tasksIds.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
    int _argIndex = 1;
    for (Integer _item : tasksIds) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __Priority_enumToString(final Priority _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case LOW: return "LOW";
      case MEDIUM: return "MEDIUM";
      case HIGH: return "HIGH";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private String __Status_enumToString(final Status _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case NEW: return "NEW";
      case IN_PROGRESS: return "IN_PROGRESS";
      case DONE: return "DONE";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private Priority __Priority_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "LOW": return Priority.LOW;
      case "MEDIUM": return Priority.MEDIUM;
      case "HIGH": return Priority.HIGH;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private Status __Status_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "NEW": return Status.NEW;
      case "IN_PROGRESS": return Status.IN_PROGRESS;
      case "DONE": return Status.DONE;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
