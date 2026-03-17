// 核心逻辑代码片段
btnFormat.setOnClickListener(v -> {
    String input = etInput.getText().toString();
    try {
        // 核心：利用 JSONObject 的 toString(4) 实现 4 空格缩进
        if (input.startsWith("[")) {
            JSONArray array = new JSONArray(input);
            tvOutput.setText(array.toString(4));
        } else {
            JSONObject object = new JSONObject(input);
            tvOutput.setText(object.toString(4));
        }
    } catch (Exception e) {
        tvOutput.setText("JSON 格式错误，请检查！");
    }
});
