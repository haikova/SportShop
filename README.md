�������
���������� �������: ������� ������� ���������� �������
�������� ������:
1.	� �������� ������, ��������� ������� ������, ����������� ����� SportEquipment. (���� ����������, ��������� �������� ����������� ����������).
public class SportEquipment {
	private Category category;
	private String title;
	private int price;

}
2.	��� ������������ ������ � �������, ������� ������������ ���� � ������, ����������� ����� RentUnit.
public class RentUnit {
	private SportEquipment[] units;

}
3.	��� �������� ���� �������, ��������� ��� ������� � ��������, ����������� ����� Shop.
public class Shop {
	private Map<SportEquipment, Integer> goods;
// Map-value - ���������� ������������� ������
}
4.	��� ������� ���������� ��������������� ������ ������ Shop ������� �� �����.

5.	�������� ���, ����������� ����� ������� �� 3-� ������ �� �������. 
- ������ ������������� �������� ��������� ���������� �� �������� � ������ �������. 
- ������ ������������� ������ ��������� ������� � ��������.
6.	���������� ����������� ������ �� ������� ������ � �������, ������� ���� ������ � ������, � � �������, ������� ������ ��������� � ��������.

�������� ����������� Java Code Convention ��� ��������� ����.
