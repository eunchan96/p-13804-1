package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;

public class WiseSayingFileRepository {
    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            int newId = getLastId() + 1;
            wiseSaying.setId(newId);
            setLastId(newId);
        }
        Map<String, Object> wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJsonStr = Util.json.toString(wiseSayingMap);
        Util.file.set("db/wiseSaying/%d.json".formatted(wiseSaying.getId()), wiseSayingJsonStr);
    }

    public WiseSaying findById(int id) {
        String wiseSayingJsonStr = Util.file.get("db/wiseSaying/%d.json".formatted(id), "");

        if (wiseSayingJsonStr.isEmpty()) {
            return null;
        }

        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJsonStr);

        return new WiseSaying(wiseSayingMap);
    }

    private int getLastId() {
        return Util.file.getAsInt("db/wiseSaying/lastId.txt", 0);
    }

    private void setLastId(int newId) {
        Util.file.set("db/wiseSaying/lastId.txt", newId);
    }

    public boolean delete(WiseSaying wiseSaying) {
        String filePath = "db/wiseSaying/%d.json".formatted(wiseSaying.getId());

        return Util.file.delete(filePath);
    }
}
