http://neerc.secna.ru/Algor/algo_base_ds_hashtab.html
0000:     /* Простая реализация HashSet'а на цепочках для интов */
0001:     class SimpleHashSet {
0002:         int[] head; // массив голов
0003:         int[] next; // массив ссылок на следующий элемент
0004:         int[] keys; // массив с ключами (вместо int'а следует поставить нужный тип)
0005:         int headNum; // количество голов
0006:         int cnt = 1; // ссылка на место для вставки нового эдемента
0007:         
0008:         /* Конструктор */
0009:         SimpleHashSet(int headNum, int maxSize) {
0010:             this.headNum = headNum;
0011:             head = new int [headNum];
0012:             next = new int [maxSize + 1];
0013:             keys = new int [maxSize + 1];
0014:         }
0015:         
0016:         /* Добавляет элемент в множество */
0017:         boolean add(int x) {
0018:             if (this.contains(x))
0019:                 return false;
0020:             int h = index(hash(x));
0021:             next[cnt] = head[h];
0022:             keys[cnt] = x;
0023:             head[h] = cnt++;
0024:             return true;
0025:         }
0026: 
0027:         /* Проверяет, содержится ли x в множестве */
0028:         boolean contains(int x) {
0029:             int h = index(hash(x));
0030:             for (int i = head[h]; i != 0; i = next[i])
0031:                 if (keys[i] == x)
0032:                     return true;
0033:             return false;
0034:         }
0035:         
0036:         /* хэш-функция (для других типов следует изменить) */
0037:         int hash(int x) {
0038:             return (x >> 15) ^ x;
0039:         }
0040:         
0041:         /* возвращает номер головы по значению хэш-функции */
0042:         int index(int hash) {
0043:             return Math.abs(hash) % headNum;
0044:         }
0045:     }
0046:     
0047:     /* Простая реализация HashSet'а методом открытой адресации для интов */
0048:     class OpenHashSet {
0049:         int FREE = Integer.MIN_VALUE;
0050:         int size;
0051:         int[] keys;
0052:         
0053:         /* Конструктор */
0054:         OpenHashSet(int size) {
0055:             this.size = Math.max(3 * size / 2, size) + 1;
0056:             keys = new int [this.size];
0057:             Arrays.fill(keys, FREE);
0058:         }
0059:         
0060:         /* Добавляет элемент в множество */
0061:         boolean add(int x) {
0062:             for (int i = index(hash(x)); ; i++) {
0063:                 if (i == size) i = 0;
0064:                 if (keys[i] == x) return false;
0065:                 if (keys[i] == FREE) {
0066:                     keys[i] = x;
0067:                     return true;
0068:                 }
0069:             }
0070:         }
0071:         
0072:         /* Проверяет, содержится ли x в множестве */
0073:         boolean contains(int x) {
0074:             for (int i = index(hash(x)); ; i++) {
0075:                 if (i == size) i = 0;
0076:                 if (keys[i] == x) return true;
0077:                 if (keys[i] == FREE) return false;
0078:             }
0079:         }
0080:         
0081:         /* хэш-функция (для других типов следует изменить) */
0082:         int hash(int x) {
0083:             return (x >> 15) ^ x;
0084:         }
0085:         
0086:         /* возвращает отступ для данного значения хэш-функции */
0087:         int index(int hash) {
0088:             return Math.abs(hash) % size;
0089:         }
0090:     }
0091: 
0092:     /* Простая реализация HashMap'а методом открытой адресации для интов */
0093:     class OpenHashMap {
0094:         int FREE = Integer.MIN_VALUE;
0095:         int size;
0096:         int[] keys;
0097:         int[] values;
0098:         
0099:         /* Конструктор */
0100:         OpenHashMap(int size) {
0101:             this.size = Math.max(3 * size / 2, size) + 1;
0102:             keys = new int [this.size];
0103:             values = new int [this.size];
0104:             Arrays.fill(keys, FREE);
0105:         }
0106:         
0107:         /* Добавляет пару в множество */
0108:         void put(int x, int y) {
0109:             for (int i = index(hash(x)); ; i++) {
0110:                 if (i == size) i = 0;
0111:                 if (keys[i] == FREE)
0112:                     keys[i] = x;
0113:                 if (keys[i] == x) {
0114:                     values[i] = y;
0115:                     return;
0116:                 }
0117:             }
0118:         }
0119:         
0120:         /* Извлекает значение */
0121:         int get(int x) {
0122:             for (int i = index(hash(x)); ; i++) {
0123:                 if (i == size) i = 0;
0124:                 if (keys[i] == FREE) throw new RuntimeException("No such key!");
0125:                 if (keys[i] == x) return values[i];
0126:             }
0127:         }
0128:         
0129:         /* Проверяет наличие пары с ключем x */
0130:         boolean containsKey(int x) {
0131:             for (int i = index(hash(x)); ; i++) {
0132:                 if (i == size) i = 0;
0133:                 if (keys[i] == FREE) return false;
0134:                 if (keys[i] == x) return true;
0135:             }
0136:         }
0137:         
0138:         /* хэш-функция (для других типов следует изменить) */
0139:         int hash(int x) {
0140:             return (x >> 15) ^ x;
0141:         }
0142:         
0143:         /* возвращает номер головы по значению хэш-функции */
0144:         int index(int hash) {
0145:             return Math.abs(hash) % size;
0146:         }
0147:     }
0148: 
0149:     /* Оптимизированный вариант HashSet'а для интов */
0150:     class FastHashMap {
0151:         int HEAD_NUM;
0152:         int MASK;
0153:         
0154:         int[] head;
0155:         int[] next;
0156:         int[] keys;
0157:         int[] values;
0158:         int cnt = 1;
0159:         
0160:         FastHashMap(int degree, int maxSize) {
0161:             HEAD_NUM = 1 << degree;
0162:             MASK = HEAD_NUM - 1;
0163:             head = new int [HEAD_NUM];
0164:             next = new int [maxSize + 1];
0165:             keys = new int [maxSize + 1];
0166:             values = new int [maxSize + 1];
0167:         }
0168:         
0169:         void put(int x, int y) {
0170:             int h = index(x);
0171:             for (int i = head[h]; i != 0; i = next[i])
0172:                 if (keys[i] == x) {
0173:                     values[i] = y;
0174:                     return;
0175:                 }
0176:             next[cnt] = head[h];
0177:             keys[cnt] = x;
0178:             values[cnt] = y;
0179:             head[h] = cnt++;
0180:         }
0181:         
0182:         int get(int x) {
0183:             int h = index(x);
0184:             for (int i = head[h]; i != 0; i = next[i])
0185:                 if (keys[i] == x)
0186:                     return values[i];
0187:             throw new RuntimeException("No such key!");
0188:         }
0189:         
0190:         boolean containsKey(int x) {
0191:             int h = index(x);
0192:             for (int i = head[h]; i != 0; i = next[i])
0193:                 if (keys[i] == x)
0194:                     return true;
0195:             return false;
0196:         }
0197:         
0198:         int index(int x) {
0199:             return Math.abs((x >> 15) ^ x) & MASK;
0200:         }
0201:     }

http://floppyy.ru/2016/08/24/hash-tables/