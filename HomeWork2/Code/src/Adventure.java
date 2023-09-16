import java.util.HashMap;

public class Adventure {
    private final int id;
    private final String name;
    private HashMap<Integer, Bottle> bottles = new HashMap<>();
    private HashMap<Integer, Equipment> equipments = new HashMap<>();

    public Adventure(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBottle(Bottle bottle) {
        bottles.put(bottle.getId(), bottle);
    }

    public void addEquipment(Equipment equipment) {
        equipments.put(equipment.getId(), equipment);
    }

    public PrintInfo removeBottle(int bottleId) {
        String name = bottles.get(bottleId).getName();
        bottles.remove(bottleId); // 根据key删除元素
        return new PrintInfo(bottles.size(), name);
    }

    public PrintInfo removeEquipment(int equipmentId) {
        String name = equipments.get(equipmentId).getName();
        equipments.remove(equipmentId);
        return new PrintInfo(equipments.size(), name);
    }

    public PrintInfo increaseStar(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);
        equipment.increaseStar();
        return new PrintInfo(equipment.getStar(), equipment.getName());
    }

    public HashMap<Integer, Bottle> getBottles() {
        return bottles;
    }

    public HashMap<Integer, Equipment> getEquipments() {
        return equipments;
    }
}
