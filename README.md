# Labirent Yol Bulma Uygulaması

Bu Java programı, bir labirentte mümkün olan bir yolun bulunması ve bu yolun ekrana veya dosyaya yazdırılmasını sağlar. Program, `HW3_1Interface` arayüzü aracılığıyla tanımlanan yöntemleri uygular.

## İşlevler ve Kullanım

Bu program, aşağıdaki işlevleri gerçekleştirir:

- `read_file(filepath)`: Belirtilen dosyadan labirent verilerini okur.
- `find_path()`: Labirentte mümkün olan bir yol bulur.
- `print_path(mypath)`: Bulunan yolu ekrana yazdırır.
- `print_path_to_file(filepath)`: Bulunan yolu belirtilen dosyaya yazdırır.

## Notlar

- Bu program, bir labirentin matris temsilini alır ve bir başlangıç noktasından bitiş noktasına mümkün olan bir yol bulur.
- Labirent, bir dosyadan okunur ve bir matris olarak temsil edilir.
- Yol bulma algoritması, bir yığın veri yapısı kullanarak gerçekleştirilir.
- Sonuçlar, dosyaya yazdırılır.
