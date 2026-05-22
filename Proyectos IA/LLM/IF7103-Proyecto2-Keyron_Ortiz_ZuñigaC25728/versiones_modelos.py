import google.generativeai as genai

genai.configure(api_key="AIzaSyDpCjvo0p9cIWBANyNxmJCeiYJ9zwqeCnw")

for model in genai.list_models():
    print(model.name)