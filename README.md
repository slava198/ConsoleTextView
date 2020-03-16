# ConsoleTextView

Консольное приложение, позволяющее вывести текст для удобного чтения путем переноса длинных строк на новую строку. Иначе говоря, все строки результирующего текста не должны превышать заданную длину. Предел длины строки по умолчанию равен 80 символам. Входной текст по умолчанию считывается приложением из стандартного потока ввода. Результат выводится в стандартный поток вывода. В случае непредвиденных ситуаций, приложение должно вывести в стандартный поток ошибок сообщение, дающее понять, что пошло не так и завершиться с статусом завершения, равным -1.

Приложение должно поддерживать следующие необязательные аргументы командной строки (могут идти в любом порядке):

·       -w <длина>

Позволяет переопределить предел длины строки по умолчанию. <Длина> должна быть целым положительным числом, иначе приложение должно вывести в стандартный поток ошибок сообщение “Length should be a positive integer value” и завершиться со статусом завершения, равным -2.

·       -s

Если очередная обрабатываемая строка входного текста должна быть перенесена после непробельного символа, и при этом уже содержит по меньшей мере один пробельный символ, совершить перенос раньше, после последнего пробельного символа. Другими словами, в этом режиме приложение должно избегать переносов строк в середине слов, при возможности.

·       <имя/путь файла>

Вместо чтения входного текста из стандартного потока ввода, считать текст из указанного файла. В случае проблем с файлом, приложение должно вывести в стандартный поток ошибок сообщение, дающее представление, что именно пошло не так, и завершиться со статусом завершения, равным -3.

Неподдерживаемые аргументы командной строки должны игнорироваться.