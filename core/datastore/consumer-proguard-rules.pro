# Keep DataStore fields
# Protocol Buffers로 자동 생성된 모든 클래스
# <fields>: 해당 클래스의 모든 필드(변수)들
# -keepclassmembers: 이 멤버들을 난독화/제거하지 말고 보호
# 데이터 직렬화가 정상 작동하도록 보장하는 필수 보호 규칙
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite* {
   <fields>;
}